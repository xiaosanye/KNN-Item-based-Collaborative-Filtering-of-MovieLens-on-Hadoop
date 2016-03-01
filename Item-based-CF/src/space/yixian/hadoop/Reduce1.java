package space.yixian.hadoop;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce1 extends Reducer<IntWritable, Text, IntWritable, Text>{

	private StringBuilder allRates;
	
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		
		allRates = new StringBuilder();
		for(Text value : values){
			//一个用户的所有评分，用“ ”分割 	 using  to split all the rates of one user
			allRates.append( value.toString() + " " );
		}
		
		//输出 <userid,moiveId-rate moiveId-rate/....>
		context.write(key, new Text(allRates.toString()));
	}
	
}
