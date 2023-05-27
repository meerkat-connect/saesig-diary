package saesigDiary.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Stack;

@Configuration
public class CustomP6SpyMessageFormatter implements MessageFormattingStrategy {

    private static final String ALLOW_FILTER = "saesgDiary";

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(CustomP6SpyMessageFormatter.class.getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        sql = formatSql(category,sql);
        LocalDateTime current = LocalDateTime.now();

        return FormatStyle.HIGHLIGHT.getFormatter().format(String.format("[%s] | %d ms | %s", category, elapsed, formatSql(category, sql)) + createStack(connectionId,elapsed));
//        return current + " | " + "수행시간 : " + elapsed + "ms" + sql;
    }

    private String formatSql(String category, String sql) {
        if (sql != null && !sql.trim().isEmpty() && Category.STATEMENT.getName().equals(category)) {
            String tmpsql = sql.trim().toLowerCase(Locale.ROOT);
            if(tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            }else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
        }

        return sql;
    }

    private String createStack(int connectionId, long elapsed) {
        Stack<String> callStack = new Stack<>();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            String trace = stackTraceElement.toString();

            if(trace.startsWith(ALLOW_FILTER)) {
                callStack.push(trace);
            }
        }

        StringBuffer sb = new StringBuffer();
        int order = 1;
        while (callStack.size() != 0) {
            sb.append("\n\t\t" + (order++) + "." + callStack.pop());
        }

        return new StringBuffer().append("\n\n\tConnection ID:").append(connectionId)
                .append(" | Excution Time:").append(elapsed).append(" ms\n")
                .append("\n\tExcution Time:").append(elapsed).append(" ms\n")
                .append("\n\tCall Stack :").append(sb).append("\n")
                .append("\n--------------------------------------")
                .toString();
    }
}
