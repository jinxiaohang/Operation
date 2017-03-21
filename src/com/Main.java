package com;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static int Tnumber=0;
    public static int Fnumber=0;
    public static void main(String[] args) {
        Statistics();
//        单独测试使用
//        StringBuffer suffix;
//        suffix=Suffix(Equation());
//        String answer=Calculate(suffix);
//        Judge(answer);
    }

    //题数统计函数
    public static void Statistics(){
        Scanner input=new Scanner(System.in);
        System.out.print("请输入您想要做的题数，按quit可退出：");
        String StringQnumber=input.next();
        while (true){
            if (StringQnumber.equals("quit")){
                System.out.println("感谢您的使用！");
                System.exit(0);
            }else if(StringQnumber.matches(".*[0-9]+.*")){
                Integer Qnumber=Integer.parseInt(StringQnumber);
                while (Qnumber>0){
                    StringBuffer suffix;
                    suffix=Suffix(Equation());
                    String answer=Calculate(suffix);
                    if (Judge(answer)){
                        Tnumber++;
                    }else {
                        Fnumber++;
                    }
                    Qnumber--;
                }
                break;
            }else {
                System.out.print("您输入的是什么意思，我看不懂，请再次输入：");
                StringQnumber=input.next();
            }
        }


        System.out.println("正确题数："+Tnumber);
        System.out.println("错误题数："+Fnumber);
        if (Fnumber+Tnumber==0){
            System.out.println("正确率为0%");
        }else {
            Double accuracy=(double)Tnumber/(Tnumber+Fnumber)*100;
            System.out.println("正确率为"+accuracy+"%");
        }

    }

    //生成四则混合运算
    public static StringBuffer Equation(){
        Random random=new Random();
        char [] Operator={'+','-','×','÷'};
        Integer num1;
        Integer num2;
        Integer num3;
        Integer num4;
        int operator;
        num1=random.nextInt(10)+1;
        num2=random.nextInt(10)+1;
        num3=random.nextInt(10)+1;
        num4=random.nextInt(10)+1;
        operator=random.nextInt(4);

        StringBuffer expression=new StringBuffer(num1.toString()+" "+Operator[operator]+" "+num2.toString());
        operator=random.nextInt(4);
        expression.append(" "+Operator[operator]+" "+num3.toString());
        operator=random.nextInt(4);
        expression.append(" "+Operator[operator]+" "+num4.toString()+'=');

        System.out.print(expression);
        return expression;
    }

    //根据中缀表达式转为后缀表达式
    public static StringBuffer Suffix(StringBuffer infix){
        Stack <Character> stack=new Stack <Character>();
        StringBuffer suffix=new StringBuffer();
        int i=0,j=0;
        char tempchar=infix.charAt(i++);
        char tempchar2=' ';
        while (tempchar!='='){
            switch (tempchar){
                case '(':
                    stack.push(tempchar);
                    tempchar=infix.charAt(i++);
                    break;
                case ')':
                    while (stack.peek()!=')'){
                        tempchar2=stack.pop();
                        suffix.append(tempchar2);
                        suffix.append(' ');
                    }
                    stack.pop();
                    tempchar=infix.charAt(i++);
                    break;
                case '+':
                case '-':
                    while (!stack.empty()&&stack.peek()!='('){
                        tempchar2=stack.pop();
                        suffix.append(tempchar2);
                        suffix.append(' ');
                    }
                    stack.push(tempchar);
                    tempchar=infix.charAt(i++);
                    break;
                case '×':
                case '÷':
                    Character ch=new Character(' ');
                    if (!stack.empty()) {
                        while((ch=stack.peek()).equals('×')||(ch=stack.peek()).equals('÷'))
                        {
                            tempchar2=stack.pop();
                            suffix.append(tempchar2);
                            suffix.append(' ');
                            if (stack.empty()) {
                                break;
                            }
                        }
                    }
                    stack.push(tempchar);
                    tempchar=infix.charAt(i++);
                    break;
                case ' ':
                    tempchar=infix.charAt(i++);
                    break;
                default:
                    while(tempchar<='9'&&tempchar>='0')
                    {
                        suffix.append(tempchar);
                        tempchar=infix.charAt(i++);
                    }
                    suffix.append(' ');
                    break;
            }


        }
        while(!stack.empty())
        {
            tempchar2=stack.pop();
            suffix.append(tempchar2);
            suffix.append(' ');
        }
        //System.out.println(suffix);
        suffix.append('\0');
        return suffix;
    }

    //根据后缀表达式计算结果（小数格式的String类型）
    public static String Calculate(StringBuffer suffix){
        int i=0;
        char tempchar=suffix.charAt(i++);
        double []answer=new double[20];
        int top=0,d;
        while (tempchar!='\0'){
            switch (tempchar){
                case '+':
                    answer[top-1]=answer[top-1]+answer[top];
                    top--;
                    tempchar=suffix.charAt(i++);
                    break;
                case '-':
                    answer[top-1]=answer[top-1]-answer[top];
                    top--;
                    tempchar=suffix.charAt(i++);
                    break;
                case '×':
                    answer[top-1]=answer[top-1]*answer[top];
                    top--;
                    tempchar=suffix.charAt(i++);
                    break;
                case '÷':
                    if(answer[top]!=0)answer[top-1]=answer[top-1]/answer[top];
                    else
                    {
                        System.out.println("\n\t除零错误!\n");
                        System.exit(0);
                    }
                    top--;
                    tempchar=suffix.charAt(i++);
                    break;
                case ' ':
                    tempchar=suffix.charAt(i++);
                    break;
                default:
                    d=0;
                    while(tempchar>='0'&&tempchar<='9')
                    {
                        d=10*d+tempchar-'0';/*将数字字符转化为对应的数值*/
                        tempchar=suffix.charAt(i++);
                    }
                    top++;
                    answer[top]=d;
                    break;
            }
        }
        //System.out.println(answer[top]);
        Double an=new Double(answer[top]);
        String Answer=new String(an.toString());
        return Answer;
    }

    //判断用户的输入是否正确，并输出正确答案（错误时输出）
    public static boolean  Judge(String answer){
        Scanner input=new Scanner(System.in);
        String UserAnswer=input.next();//接收用户输入

        //System.out.println(UserAnswer.matches(".\\p{Punct}.*"));//正则表达式测试使用
        while(true){
            if(UserAnswer.matches(".*[a-zA-Z]+.*")){//含有字母的用户输入
                if(UserAnswer.indexOf("quit")!=-1){//程序退出输入
                    System.out.println("程序退出");
                    System.out.println("正确题数："+Tnumber);
                    System.out.println("错误题数："+Fnumber);
                    if (Fnumber+Tnumber==0){
                        System.out.println("正确率为0%");
                    }else {
                        Double accuracy=(double)Tnumber/(Tnumber+Fnumber)*100;
                        System.out.println("正确率为"+accuracy+"%");
                    }
                    System.out.println("感谢您的使用！");
                    System.exit(0);
                }else {//非法的输入
                    System.out.println("非法输入，请再次输入");
                    UserAnswer=input.next();
                }
            }else {//不含字母的用户输入
                if (UserAnswer.matches(".*\\p{Punct}.*")){//含有标点符号的用户输入(符号，小数点，除号和其他符号)
                    if (UserAnswer.indexOf('/')!=-1&&(UserAnswer.indexOf('/')==UserAnswer.lastIndexOf('/'))){//含有除号的用户输入
                        int sign=UserAnswer.indexOf('/');
                        String StringNumerator=UserAnswer.substring(0,sign);
                        String StringDenominator=UserAnswer.substring(sign+1);
                        Integer integerNumerator=Integer.parseInt(StringNumerator);
                        Integer integerDenominator=Integer.parseInt(StringDenominator);
                        if(integerDenominator==0){
                            System.out.println("分母不能为零，请再次输入");
                            UserAnswer=input.next();
                        }else {
                            Double an=new Double((double)integerNumerator/integerDenominator);
                            String DisposeUserAnswer=new String(an.toString());
                            if (DisposeUserAnswer.equals(answer)) {
                                System.out.println("true");
                                return true;
                            }else {
                                System.out.print("false ");
                                System.out.println("正确答案是："+answer);
                                return false;
                            }
                        }
                    }else if(UserAnswer.indexOf('.')!=-1&&(UserAnswer.indexOf('.')==UserAnswer.lastIndexOf('.'))){//含有小数点的输入
                        if(answer.equals(UserAnswer)){
                            System.out.println("true");
                            return true;
                        }else {
                            System.out.print("false ");
                            System.out.println("正确答案是："+answer);
                            return false;
                        }
                    }else if((UserAnswer.indexOf('-')==0||UserAnswer.indexOf('+')==0)
                            &&(UserAnswer.lastIndexOf('-')<=0)
                            &&(UserAnswer.lastIndexOf('+')<=0)){
                        int sign=answer.indexOf('.');
                        String StringInteger =answer.substring(0,sign);
                        Integer integer=Integer.parseInt(StringInteger);
                        try {
                            Integer integerUserAnswer=Integer.parseInt(UserAnswer);
                            if(integer.equals(integerUserAnswer)){
                                System.out.println("true");
                                return true;
                            }else{
                                System.out.print("false ");
                                System.out.println("正确答案是："+answer);
                                return false;
                            }
                        }catch (Exception e){
                            System.out.println("非法输入，请再次输入");
                            UserAnswer=input.next();
                        }


                    }else {
                        System.out.println("非法输入，请再次输入");
                        UserAnswer=input.next();
                    }
                }else { //整数输入
                    int sign=answer.indexOf('.');
                    String StringInteger =answer.substring(0,sign);
                    Integer integer=Integer.parseInt(StringInteger);
                    Integer integerUserAnswer=Integer.parseInt(UserAnswer);
                    if(integer.equals(integerUserAnswer)){
                        System.out.println("true");
                        return true;
                    }else{
                        System.out.print("false ");
                        System.out.println("正确答案是："+answer);
                        return false;
                    }
                }
            }
        }


    }
}

