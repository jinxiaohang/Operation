package com;

import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		int N=5;//�����û���������
		int i=0;//����ѭ��
		int T=0,F=0;//��¼��ȷ����������
		
		Main it=new Main();
		
		Scanner input=new Scanner(System.in);
        System.out.println("Please enter the number of questions you want to do");
        N=input.nextInt();
        
      //���Ʋ�������
        while(i<N){
        	//�������һ������
            int num = (int)(Math.random()*7);
            //int num = 7;
            i++;
            switch (num){
            	case 0://�����ӷ�
            		if(it.add()){T++;}
            		else{F++;}
            		break;
            	case 1://��������
            		if(it.sub()){T++;}
            		else{F++;}
            		break;
            	case 2://�����˷�
            		if(it.mul()){T++;}
            		else{F++;}
            		break;
            	case 3://��������
            		if(it.div()){T++;}
            		else{F++;}
            		break;
            	case 4://������ӷ�
            		if(it.tadd()){T++;}
            		else{F++;}
            		break;
            	case 5://���������
            		if(it.tsub()){T++;}
            		else{F++;}
            		break;
            	case 6://������˷�
            		if(it.tmul()){T++;}
            		else{F++;}
            		break;	
            	case 7://���������
            		if(it.tdiv()){T++;}
            		else{F++;}
            		break;	
            }
        }
        System.out.println("������"+T+"��");
        System.out.println("������"+F+"��");
        float an = (float) T/N;
        System.out.println("��ȷ��Ϊ"+an*100+"%");
	}


	@SuppressWarnings("resource")
	private boolean div() {
		int a = (int)(Math.random()*10)+1;
        int b = (int)(Math.random()*10)+1;
        int d1 = a/b;

        System.out.print(a+" / "+b+"=");
        Scanner input=new Scanner(System.in);
		int c1 = (int) input.nextFloat();
        if(c1==d1){
            System.out.println("ture");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
	}







	@SuppressWarnings("resource")
	private boolean add() {
		int a = (int)(Math.random()*10)+1;
        int b = (int)(Math.random()*10)+1;
        int d = a+b;

        System.out.print(a+"+"+b+"=");
        Scanner input=new Scanner(System.in);
		int c = input.nextInt();
        if(c==d){
            System.out.println("ture");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
	}
	
	@SuppressWarnings("resource")
	private boolean sub() {
		int a = (int)(Math.random()*10)+1;
        int b = (int)(Math.random()*10)+1;
        int d = a-b;

        System.out.print(a+"-"+b+"=");
        Scanner input=new Scanner(System.in);
		int c = input.nextInt();
        if(c==d){
            System.out.println("ture");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
	}
	
	
	@SuppressWarnings("resource")
	private boolean mul() {
		int a = (int)(Math.random()*10)+1;
        int b = (int)(Math.random()*10)+1;
        int d = a*b;

        System.out.print(a+"*"+b+"=");
        Scanner input=new Scanner(System.in);
		int c = input.nextInt();
        if(c==d){
            System.out.println("ture");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
	}
	
	private boolean tadd(){
		int fz1,fm1,fz2,fm2;
		while(true){
            fz1=(int)(Math.random()*10)+1;
            fm1=(int)(Math.random()*10)+1;
            fz2=(int)(Math.random()*10)+1;
            fm2=(int)(Math.random()*10)+1;
            if(fz1<fm1&&fz2<fm2){
                break;
            }
        }
        System.out.print("("+fz1+"/"+fm1+")"+"+"+"("+fz2+"/"+fm2+")"+"=");
        int tempfm = fm1*fm2;
        int tempfz = fz1*fm2+fz2*fm1;
        int max = tempfm;
        String answer;
		if(tempfm<tempfz){
            max=getmax(tempfz,tempfm);
        }else if(tempfm>tempfz){
            max=getmax(tempfm,tempfz);
        }else{
            answer="1";
        }
        tempfz=tempfz/max;
        tempfm=tempfm/max;
        if(0==tempfz%tempfm){
            Integer s=tempfz/tempfm;//���Ϊ����
            answer=s.toString();
        }else{
            answer=tempfz+"/"+tempfm;
        }

        Scanner input=new Scanner(System.in);
		String reanswer = input.next();

        if(reanswer.equals(answer)){
            System.out.println("ture");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
	}
	
	
	private boolean tsub() {
		int fz1,fm1,fz2,fm2;
		while(true){
            fz1=(int)(Math.random()*10)+1;
            fm1=(int)(Math.random()*10)+1;
            fz2=(int)(Math.random()*10)+1;
            fm2=(int)(Math.random()*10)+1;
            if(fz1<fm1&&fz2<fm2){
                break;
            }
        }
        System.out.print("("+fz1+"/"+fm1+")"+"-"+"("+fz2+"/"+fm2+")"+"=");
        int tempfm = fm1*fm2;
        int tempfz = fz1*fm2-fz2*fm1;
        int max = tempfm;
        String answer;
        if(tempfz!=0){
            if(Math.abs(tempfm)<Math.abs(tempfz)){
                max=getmax(Math.abs(tempfz),Math.abs(tempfm));
            }else if(Math.abs(tempfm)>Math.abs(tempfz)){
                max=getmax(Math.abs(tempfm),Math.abs(tempfz));
            }else{
                answer="1";
            }
            tempfz=tempfz/max;
            tempfm=tempfm/max;
            if(0==tempfz%tempfm){
            	Integer s=tempfz/tempfm;//���Ϊ����
            	answer=s.toString();
            }else{
            	answer=tempfz+"/"+tempfm;
            }

            Scanner input=new Scanner(System.in);
            String reanswer = input.next();

            if(reanswer.equals(answer)){
            	System.out.println("ture");
            	return true;
            }else{
            	System.out.println("false");
            	return false;
            }
        }else{
        	answer="0";
        	Scanner input=new Scanner(System.in);
            String reanswer = input.next();

            if(reanswer.equals(answer)){
            	System.out.println("ture");
            	return true;
            }else{
            	System.out.println("false");
            	return false;
            }
        }
           
	}
	
	private boolean tmul() {
		int fz1,fm1,fz2,fm2;
		while(true){
            fz1=(int)(Math.random()*10)+1;
            fm1=(int)(Math.random()*10)+1;
            fz2=(int)(Math.random()*10)+1;
            fm2=(int)(Math.random()*10)+1;
            if(fz1<fm1&&fz2<fm2){
                break;
            }
        }
		System.out.print("("+fz1+"/"+fm1+")"+"*"+"("+fz2+"/"+fm2+")"+"=");
        int tempfm = fm1*fm2;
        int tempfz = fz1*fz2;
        int max = tempfm;
        String answer;
        if(tempfm<tempfz){
            max=getmax(tempfz,tempfm);
        }else if(tempfm>tempfz){
            max=getmax(tempfm,tempfz);
        }else{
            answer="1";
        }
        tempfz=tempfz/max;
        tempfm=tempfm/max;
        if(0==tempfz%tempfm){
            Integer s=tempfz/tempfm;//���Ϊ����
            answer=s.toString();
        }else{
            answer=tempfz+"/"+tempfm;
        }
        Scanner input=new Scanner(System.in);
        String reanswer = input.next();

        if(reanswer.equals(answer)){
        	System.out.println("ture");
        	return true;
        }else{
        	System.out.println("false");
        	return false;
        }
	}

	private boolean tdiv() {
		int fz1,fm1,fz2,fm2;
		while(true){
            fz1=(int)(Math.random()*10)+1;
            fm1=(int)(Math.random()*10)+1;
            fz2=(int)(Math.random()*10)+1;
            fm2=(int)(Math.random()*10)+1;
            if(fz1<fm1&&fz2<fm2){
                break;
            }
        }
		System.out.print("("+fz1+"/"+fm1+")"+" / "+"("+fz2+"/"+fm2+")"+"=");
        int tempfm = fm1*fz2;
        int tempfz = fz1*fm2;
        int max = tempfm;
        String answer;
        if(tempfm<tempfz){
            max=getmax(tempfz,tempfm);
        }else if(tempfm>tempfz){
            max=getmax(tempfm,tempfz);
        }else{
            answer="1";
        }
        tempfz=tempfz/max;
        tempfm=tempfm/max;
        if(0==tempfz%tempfm){
            Integer s=tempfz/tempfm;//���Ϊ����
            answer=s.toString();
        }else{
            answer=tempfz+"/"+tempfm;
        }
        Scanner input=new Scanner(System.in);
        String reanswer = input.next();

        if(reanswer.equals(answer)){
        	System.out.println("ture");
        	return true;
        }else{
        	System.out.println("false");
        	return false;
        }
	}

	
	//�����������ķ���
    public static int getmax(int divisor,int dividend) {
        int remainder=divisor%dividend;
        boolean i=true;
        if(remainder==0){
            i=false;
        }
        while(i){
            divisor=dividend;
            dividend=remainder;
            remainder=divisor%dividend;
            if(remainder==0){
                i=false;
            }
        }
        return dividend;
    }
}
