// AidlInterface.aidl
package com.ngyb.service;

// Declare any non-default types here with import statements

interface AidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

        void callTest();

        boolean buyBeans(String name,String phone,int money);
}
