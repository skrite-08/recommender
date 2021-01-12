package com.sun.method.dynamic;

public class Similarity {

	public static float calculateSimilarity(float[] curRating, float[] otherRating) {
		// TODO Auto-generated method stub
		float similarity=0f;
		int len=curRating.length;
		int cnt=0;

		float denominator;

		float sum_xy=0;
		float sum_x=0;
		float sum_y=0;

		float sum_x2=0;
		float sum_y2=0;

		for(int i=0;i<len;i++) {
			//根据被当前用户“评分”了的项目来计算相似度，由于浮点数不能==比较，这里用0.01f表示0.0f
			if(curRating[i]>0.01f) {
				cnt++;

				float x=curRating[i];
				float y=otherRating[i];
				sum_x+=x;
				sum_y+=y;
				sum_xy+=x*y;
				sum_x2+=Math.pow(x,2);
				sum_y2+=Math.pow(y,2);

			}
		}

		if(cnt!=0) {
			 denominator = (float) (Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / cnt) * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / cnt));
		}
		else{
			denominator = 0;
		}

		if(denominator==0){
			return 0;
		}
		else {
			similarity = (sum_xy - (sum_x * sum_y) / cnt) / denominator;
			return similarity;
		}
	}

}
