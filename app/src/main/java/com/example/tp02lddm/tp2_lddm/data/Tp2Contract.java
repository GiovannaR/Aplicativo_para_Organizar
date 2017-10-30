package com.example.tp02lddm.tp2_lddm.data;

import android.provider.BaseColumns;

/**
 * Created by giovannariqueti on 29/10/17.
 */

public class Tp2Contract {
    public static final class SubjectEntry implements BaseColumns {
        public static final String TABLE_NAME = "subject";
        public static final String COLUMN_SUBJECT_NAME = "subjectName";
        public static final java.lang.String COLUMN_TIMESTAMP = "timestamp";
    }

    public static final class ContainerEntry implements BaseColumns {
        public static final String TABLE_NAME = "container";
        public static final String COLUMN_CONTAINER_NAME = "containerName";
        public static final String COLUMN_FK_SUBJECT_ID = "subjectId";
    }

    public static final class LinkEntry implements BaseColumns {
        public static final String TABLE_NAME = "link";
        public static final String COLUMN_LINK_NAME = "linkName";
        public static final String COLUMN_LINK_ADDRESS = "linkAddress";
        public static final String COLUMN_FK_CONTAINER_ID = "containerID";
    }



}
