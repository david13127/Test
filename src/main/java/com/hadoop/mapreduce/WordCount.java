package com.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration(true);

		Job job = Job.getInstance(conf);

		// Create a new Job
		// Job job = Job.getInstance();

		//必须必须写的
		job.setJarByClass(WordCount.class);

		// Specify various job-specific parameters
		job.setJobName("wordcount");

		// job.setInputPath(new Path("in"));
		// job.setOutputPath(new Path("out"));

		Path infile = new Path("/data/wc/input");
		TextInputFormat.addInputPath(job, infile);

		Path outfile = new Path("/data/wc/output");
		if (outfile.getFileSystem(conf).exists(outfile)) {
			outfile.getFileSystem(conf).delete(outfile, true);
		}
		TextOutputFormat.setOutputPath(job, outfile);

		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setReducerClass(MyReducer.class);

		// Submit the job, then poll for progress until the job is complete
		job.waitForCompletion(true);

	}

}