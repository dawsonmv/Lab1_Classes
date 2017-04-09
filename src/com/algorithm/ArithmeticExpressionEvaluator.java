/**
 * 
 * Arithmetic Expression Evaluator
 * 
 * Converts Infix to Postfix Expression
 * 
 * based loosley on the Edsger Dijskras
 *  'Shunting-Yard' Algorithm
 * 
 *  for final evaluation uses reg-ex and String split
 * 
 */
package com.algorithm;

/**
 * @author Dawson Valdes
 *
 * Berkeley City College
 * 
 * Data Structure and Algorithms
 * 
 * Spring 2017
 *
 */

import com.data.Stack;

public class ArithmeticExpressionEvaluator 
{
	
	public ArithmeticExpressionEvaluator()
	{
		
	}
				
	private boolean is_operator( char c  )
	{		
		switch(c)
    	{
			case '+':return true;
			case '-':return true;
			case '*':return true;
			case '/':return true;
			case '^':return true;
			
			default: return false;	
    	}		
	}
	
	private boolean is_number( char c )
	{		
		switch(c)
		{
			case '0':return true;
			case '1':return true;
			case '2':return true;
			case '3':return true;
			case '4':return true;
			case '6':return true;
			case '7':return true;
			case '8':return true;
			case '9':return true;
			
			default:return false;
		}		
	}
	
	private int precedence( char c )
	{	
		switch(c)
		{
			case '+':return 2;
			case '-':return 2;
			case '*':return 3;
			case '/':return 3;
			case '^':return 4;
			default: return -1;
		}		
	}
	
	public String infix_to_postfix( String expression )
	{
		
	    //make a stack for chars  
		Stack<Character> operators = new Stack<Character>();
		
		//make an output string
		String output = new String();
					
		//scan the expression from left to right. 
	    for( int i = 0 ; i < expression.length() ; i++ )
	    {
	    	
	    	// store the char from the input string at index i into a temp var
	    	char c = expression.charAt( i );
	    		    		    	
	    	// if the char is a number add it to the output string
	    	if( is_number(c) )
	    	{
	    		output = output + c;
	    	}
	    	
	    	// otherwise if the char is a left parenthesis push it to the stack
	    	else if( c == '(' )
	    	{
	    		operators.push(c);
	    	}
	    	
	    	//otherwise if it is an operator 
	    	else if( is_operator(c))
	    	{
	    		
	    		// pop the top operator
	    		char top = operators.pop();
	    		
	    		//if the precedence of c is greater than the precedence of the top operator
	    		if ( precedence(c) > precedence(top))
	    		{
	    			operators.push(top);
	    			operators.push(c);
	    		}
	    		else
	    		{
	    			operators.push(c);
	    			operators.push(top);
	    		}
	    		
	    		// append a space to the output string to separate the numbers
	    		output = output + " "; 
	    		
	    	}
	    	// otherwise if it is a right parenthesis
	       	else if ( c == ')' )
	    	{
	    		// pop the stack onto a temp char
	    		char operator = operators.pop();
	    		
	    		// while the operator is not a left parenthesis and the stack is not empty
	    		while ( operator != '('  )
	    		{
	    			// append the operator to the output string
	    			output = output + " " + operator; 	    			
	    			// pop the next operator from the stack
	    			operator = operators.pop();
	    		}
	    	}   		
	    }
	    
	    while( operators.is_empty() == false )
	    {
	    	output = output + " " + operators.pop();
	    }
	    
	    return output;
	    
	}
	
	
	public String evaluate_postfix( String expression )
	{
		//String 
		Stack<String> result = new Stack<String>();
		
		String[] postfix = expression.split(" ");
		
		for( int i = 0 ; i < postfix.length ; i++ )
		{
			if( postfix[i].matches("\\d+") )
			{
				result.push(postfix[i]);
			}
			else if ( postfix[i].matches("[/+*-/^]") )
			{
				int a = Integer.parseInt( result.pop() );
				int b = Integer.parseInt( result.pop() );
				String c = evaluate( a , b , postfix[i] );
				result.push( c );
			}
		}
		
		return result.pop();
		
	}
	
	private String evaluate( int a , int b , String op )
	{
		
		int result = 0;
			
		switch( op )
		{
			
			case "+":
				result = b + a;
				break;
					
			case "-":
				result = b - a;
				break;
					
			case "*":
				result = b * a;
				break;
					
			case "/":
				result = b / a;
				break;
				
			case "^":
				result = b^a;
				break;
				
			default: return "error";	
			
		}
		
		return String.valueOf(result);
	
	}	
	
	
}
