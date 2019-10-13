package com.ngyb.contentproviderhandle;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/9 14:06
 */
public class ContractUtils {
    private static final String TAG = "ContractUtils";

    public static List<Contract> queryContract(Context context) {
        List<Contract> contracts = new ArrayList<>();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri uri1 = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
        Log.e(TAG, "queryContract: " + (cursor == null));
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Contract contract = new Contract();
                String contactid = cursor.getString(0);
                Log.e(TAG, "queryContract: "+contactid );
                Cursor query = context.getContentResolver().query(uri1, new String[]{"data1", "mimetype"}, "raw_contact_id=?",
                        new String[]{contactid}, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        String data1 = query.getString(0);
                        String mimetype = query.getString(1);
                        Log.e(TAG, "queryContract: "+data1+"===="+mimetype );
                        if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                            contract.setPhone(data1);
                        } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                            contract.setName(data1);
                        } else if (mimetype.equals("vnd.android.cursor.item/email_v2")) {
                            contract.setEmail(data1);
                        }
                    }
                }
                contracts.add(contract);
            }
        }
        return contracts;
    }
}
