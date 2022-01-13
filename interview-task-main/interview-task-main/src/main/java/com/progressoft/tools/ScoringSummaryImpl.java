package com.progressoft.tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ScoringSummaryImpl implements ScoringSummary {
 private List<BigDecimal> bigDecimals;
 public ScoringSummaryImpl(List<BigDecimal> bigDecimals) {
	 this.bigDecimals=new ArrayList<>(bigDecimals);
	}
	@Override
	public BigDecimal mean() {
	double result=0.0;
		for(BigDecimal c : this.bigDecimals) {
		result+=c.doubleValue();
		}
		result/=bigDecimals.size();
		return new BigDecimal(result);
	}

	@Override
	public BigDecimal standardDeviation() {
		
		return new BigDecimal(Math.sqrt(this.variance().doubleValue()));
	}

	@Override
	public BigDecimal variance() {
		double Mean=this.mean().doubleValue();
		double result=0.0;
		for(BigDecimal c : this.bigDecimals) {
			result+=((c.doubleValue()-Mean)*(c.doubleValue()-Mean));
			}
		result/=this.bigDecimals.size();
		
		return new BigDecimal(result);
	}

	@Override
	public BigDecimal median() {
		this.sort();
		double result=0.0;
		if(bigDecimals.size()%2==0) {
			result=bigDecimals.get(bigDecimals.size()/2).doubleValue()+bigDecimals.get((bigDecimals.size()/2)+1).doubleValue();
			result/=2;
		}
		else {
			result=bigDecimals.get(bigDecimals.size()/2).doubleValue();
		}
		
		return new BigDecimal(result);
	}
    public void sort(){
    	   for(int i = 0; i < bigDecimals.size()-1; i++){
    	        for(int j = 1; j <bigDecimals.size(); j++){
    	            if(bigDecimals.get(i).compareTo(bigDecimals.get(j)) == -1){
    	                BigDecimal temp = bigDecimals.get(j);
    	                bigDecimals.set(i, bigDecimals.get(i));
    	                bigDecimals.set(i, temp);
    	            }
    	        }
    	    }
    	
    	
    } 
	@Override
	public BigDecimal min() {
		this.sort();
		return bigDecimals.get(0);
	}

	@Override
	public BigDecimal max() {
		this.sort();
		return bigDecimals.get(bigDecimals.size()-1);
	}

}
