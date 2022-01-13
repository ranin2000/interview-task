package com.progressoft.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NormalizerImpl implements Normalizer {

	@Override
	public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize) {
		List <BigDecimal> result=new ArrayList<>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath.toString()));
			String Line="";
			int count=0, num=0;
			List<BigDecimal>d=new ArrayList<>();
			while ((Line = csvReader.readLine()) != null) {
			    String[] data = Line.split(",");
			if(data[0].equals(colToStandardize)) {
				num=count;}
			d.add(new BigDecimal(Double.parseDouble(data[num])));
			count++;
			}
			ScoringSummary s=new ScoringSummaryImpl(d);
			double mean=s.mean().doubleValue();
			double stander=s.standardDeviation().doubleValue();
			for(int i=0;i<d.size();i++) {
				result.add(new BigDecimal(((d.get(i).doubleValue()-mean)/stander)));
			}
			int n=0;
			BufferedWriter csvWriter=new BufferedWriter((new FileWriter(destPath.toString())));
			while ((Line = csvReader.readLine()) != null) {
				csvWriter.newLine();
			    csvWriter.append(Line);
			    if(n!=0) {
			    csvWriter.append(result.get(n).toString());	
			    }
			    else {
			    	csvWriter.append(colToStandardize+" z-score");
			    }
			    			   
			}
			csvReader.close();
			csvWriter.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return new ScoringSummaryImpl(result);
	}

	@Override
	public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize) {
		List <BigDecimal> result=new ArrayList<>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath.toString()));
			String Line="";
			int count=0, num=0;
			List<BigDecimal>d=new ArrayList<>();
			while ((Line = csvReader.readLine()) != null) {
			    String[] data = Line.split(",");
			if(data[0].equals(colToNormalize)) {
				num=count;}
			d.add(new BigDecimal(Double.parseDouble(data[num])));
			count++;
			}
			ScoringSummary s=new ScoringSummaryImpl(d);
			double min=s.min().doubleValue();
			double max=s.max().doubleValue();
			for(int i=0;i<d.size();i++) {
				result.add(new BigDecimal((d.get(i).doubleValue()-min)/(max-min)));
			}
			int n=0;
			BufferedWriter csvWriter=new BufferedWriter((new FileWriter(destPath.toString())));
			while ((Line = csvReader.readLine()) != null) {
				csvWriter.newLine();
			    csvWriter.append(Line);
			    if(n!=0) {
			    csvWriter.append(result.get(n).toString());	
			    }
			    else {
			    	csvWriter.append(colToNormalize+" min-max Scaling");
			    }
			    			   
			}
			csvReader.close();
			csvWriter.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return new ScoringSummaryImpl(result);
	}}
