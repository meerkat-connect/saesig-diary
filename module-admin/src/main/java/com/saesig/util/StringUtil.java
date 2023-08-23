package com.saesig.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isSqlInjectionSafe(String dataString){
        String sqlTypes =
                "TABLE, TABLESPACE, PROCEDURE, FUNCTION, TRIGGER, KEY, VIEW, MATERIALIZED VIEW, LIBRARY" +
                        "DATABASE LINK, DBLINK, INDEX, CONSTRAINT, TRIGGER, USER, SCHEMA, DATABASE, PLUGGABLE DATABASE, BUCKET, " +
                        "CLUSTER, COMMENT, SYNONYM, TYPE, JAVA, SESSION, ROLE, PACKAGE, PACKAGE BODY, OPERATOR" +
                        "SEQUENCE, RESTORE POINT, PFILE, CLASS, CURSOR, OBJECT, RULE, USER, DATASET, DATASTORE, " +
                        "COLUMN, FIELD, OPERATOR";

        String[] sqlRegexps = {
                "(?i)(.*)(\\b)+SELECT(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+INSERT(\\b)+\\s.*(\\b)+INTO(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+UPDATE(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+DELETE(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+UPSERT(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+SAVEPOINT(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+CALL(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+ROLLBACK(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+KILL(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+DROP(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+CREATE(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+ALTER(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+TRUNCATE(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+LOCK(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+UNLOCK(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+RELEASE(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+DESC(\\b)+(\\w)*\\s.*(.*)",
                "(?i)(.*)(\\b)+DESCRIBE(\\b)+(\\w)*\\s.*(.*)",
                "(.*)(/\\*|\\*/|;){1,}(.*)",
                "(.*)(-){2,}(.*)"
        };

        if(dataString == null || dataString.length() == 0){
            return true;
        }

        List<Pattern> patterns = new ArrayList<Pattern>();
        for(String sqlExpression : sqlRegexps){
            patterns.add(Pattern.compile(sqlExpression, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
        }

        for(Pattern pattern : patterns){
            if(pattern.matcher(dataString).matches()){
                return false;
            }
        }
        return true;
    }
}