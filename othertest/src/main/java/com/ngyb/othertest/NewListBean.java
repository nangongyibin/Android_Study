package com.ngyb.othertest;

import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 14:34
 */
public class NewListBean {

    /**
     * data : {"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10007/list_2.json","news":[{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"/10007/2078369924F9UO.jpg","listimage1":"/10007/1506815057D99I.jpg","listimage2":"/10007/1506815057D99I.jpg","listimage3":"/10007/1506815057D99I.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"/10007/1509585620ASS3.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"/10007/1506815057D99I.jpg","listimage1":"/10007/1506815057D99I.jpg","listimage2":"/10007/1506815057D99I.jpg","listimage3":"/10007/1506815057D99I.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":"1","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"/10007/1505891536Z82T.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"/10007/1483727032VMXT.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"/10007/1481879990BEMG.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"/10007/14800329488K7F.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"/10007/14791094274LT9.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"/10007/1478185906G9WQ.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"/10007/1477262385PASS.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":"0","url":"/10007/724D6A55496A11726628.html"}]}
     * retcode : 200
     */

    private DataBean data;
    private int retcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public static class DataBean {
        /**
         * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
         * more : /10007/list_2.json
         * news : [{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"/10007/2078369924F9UO.jpg","listimage1":"/10007/1506815057D99I.jpg","listimage2":"/10007/1506815057D99I.jpg","listimage3":"/10007/1506815057D99I.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"/10007/1509585620ASS3.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"/10007/1506815057D99I.jpg","listimage1":"/10007/1506815057D99I.jpg","listimage2":"/10007/1506815057D99I.jpg","listimage3":"/10007/1506815057D99I.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":"1","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"/10007/1505891536Z82T.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"/10007/1483727032VMXT.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"/10007/1481879990BEMG.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"/10007/14800329488K7F.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"/10007/14791094274LT9.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"/10007/1478185906G9WQ.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":"0","url":"/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"/10007/1477262385PASS.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":"0","url":"/10007/724D6A55496A11726628.html"}]
         */

        private String countcommenturl;
        private String more;
        private List<NewsBean> news;

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class NewsBean {
            /**
             * comment : true
             * commentlist : /10007/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
             * id : 35311
             * listimage : /10007/2078369924F9UO.jpg
             * listimage1 : /10007/1506815057D99I.jpg
             * listimage2 : /10007/1506815057D99I.jpg
             * listimage3 : /10007/1506815057D99I.jpg
             * pubdate : 2014-10-1113:18
             * title : 网上大讲堂第368期预告：义务环保人人有责
             * type : 0
             * url : /10007/724D6A55496A11726628.html
             */

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String listimage;
            private String listimage1;
            private String listimage2;
            private String listimage3;
            private String pubdate;
            private String title;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getListimage1() {
                return listimage1;
            }

            public void setListimage1(String listimage1) {
                this.listimage1 = listimage1;
            }

            public String getListimage2() {
                return listimage2;
            }

            public void setListimage2(String listimage2) {
                this.listimage2 = listimage2;
            }

            public String getListimage3() {
                return listimage3;
            }

            public void setListimage3(String listimage3) {
                this.listimage3 = listimage3;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
