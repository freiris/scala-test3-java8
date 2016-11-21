import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yahoo.presto.ilp.core.classifier.Classifiers;
import com.yahoo.presto.ilp.core.classifier.ILPClassifier;
import com.yahoo.presto.ilp.core.classifier.ModelOutput;

import org.json.JSONObject;

public class PredictionRun {
    protected static final String DATE_TEXT =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)|" +
                    "(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)|" +
                    "((19|20)\\d\\d)-(0?[1-9]|[12][0-9]|3[01])|" +
                    "((19|20)\\d\\d)-(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])";
    protected static final String TREND_TEXT = "fall|fell|drop|down|low|rise|up|high";
    protected static final String QUANT_TEXT = "((\\d+\\.)?\\d+)";
    protected static final String RELATIONSHIP_TEXT = "competitor|customer|supplier|partner";

    protected static final Pattern DATE_PATTERN = Pattern.compile(DATE_TEXT);
    protected static final Pattern TREND_PATTERN = Pattern.compile(TREND_TEXT);
    protected static final Pattern QUANT_PATTERN = Pattern.compile(QUANT_TEXT);
    protected static final Pattern RELATIONSHIP_PATTERN = Pattern.compile(RELATIONSHIP_TEXT);

    //public static String predictionRun(String question, String uuid) throws Exception{
    public  String predictionRun(String question, String uuid) throws Exception{

        //String question = "VM keeps in migrating state, and can not finish migration";
        //String prefix = "/home/ubuntu/vdisk/QandA201610/v1/";
        String prefix = "/Users/yongxiang/Desktop/Spark/scala-test3-java8/src/main/java/v1/";
 	String modelDir = prefix + "modelDir";
        String dictDir = prefix + "dictDir";
        String modelName = "miaotest20161001";
        /*
         * when MSFT goes up 1 percent show all the competitors of CSCO show all the related
         * companies of CSCO which regions do aapl have income from where comes GOOGL's income
         */
        ILPClassifier ilpClassifier = Classifiers.load(new File(modelDir), modelName, new File(dictDir));
        ModelOutput outIntent = ilpClassifier.classify(question);
        String intent = outIntent.getBestMatch().getIntent();
//        String ticker = outIntent.getEntityMatches().get(0).getEntity().getName();

        //String uuid = "xxx-yyy";
        // 按指定模式在字符串查找
        String line = intent;
        //String pattern = "(.*)(\\d+)(.*)";
        String pattern = "MiaoTest20161001.getVM(.*)Info(.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (!m.find( )) 
            System.out.println("NO MATCH");

        JSONObject jo = new JSONObject();
        jo.put("argument", m.group(2));
        jo.put("uuid", uuid);
        jo.put("kind",m.group(1));
        //System.out.println("Intent = " + intent);
        //System.out.println("json:"+jo.toString());
        return jo.toString();
    }

}
