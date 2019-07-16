package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DaoGeneratorDemo {
    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1, "wangjie.com.library.bean");
        schema.setDefaultJavaPackageDao("wangjie.com.library.dao");
        addNote(schema);

        new DaoGenerator().generateAll(schema, "E:/ijkplayer/NewProject/library/src/main/java-gen");
    }

    /**
     * @param schema  LogInfo
     */
    private static void addNote(Schema schema) {
        System.out.println("准备添加ll");
        Entity note  = schema.addEntity("LogInfoBean");

        note.addIdProperty().autoincrement();
        note.addStringProperty("logInfo").notNull();
        note.addStringProperty("log").notNull();
        note.addStringProperty("ll").notNull();

    }
}
