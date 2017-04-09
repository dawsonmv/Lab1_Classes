/**
 * 
 * Generic Stack
 * with 
 * singularly linked node
 * using
 * encapsulation
 * 
 */

package com.data;

/**
 * @author Dawson Valdes
 *
 *	Berkeley City College
 *
 *	Data Structure and Algorithms
 *
 *	Spring 2017
 *
 */

public class Stack<ItemType> 
{

	private class Node
	{
		
		private ItemType _item; // pointer to item
		private Node _next; // pointer to next node
		
		public Node(ItemType item , Node next)
		{
			_item = item; // points to the initial item
			_next = next; // points to the next item
		}
		
		public ItemType item()
		{
			return _item; // returns pointer to item
		}
		
		public Node next()
		{
			return _next; // returns pointer to next node
		}
		
		public void set_item(ItemType item)
		{
			_item = item; // sets new pointer to item
		}
		
		public void set_next(Node next)
		{
			_next = next; // sets new pointer to next node
		}
		
	}
	
	private Node head; // pointer to head of stack
	private int size; // size of the stack
	
	public Stack()
	{
		head = null; // points to a null
		size = 0; // sets size to 0
	}
	
	public void push(ItemType item)
	{
		Node new_node = new Node( item , head ); // creates 
		head = new_node; // points the head of the stack to the new node
		size++; // increament the size of the structure
	}
	
	public ItemType pop()
	{
		if ( size > 0 ) // if there is something in the stack
		{
			// create a pointer  called old_head to the node that points to the head
			Node old_head = head; 
			// create a pointer called pop_item to the item the old_head points to
			ItemType pop_item = old_head.item(); 
		
			// point the head to the where the next of the old_head points to
			head = old_head.next();
			
			// remove the old_head next reference by setting it to null
			old_head.set_next(null);
			// remove the old_head item refrence by setting it to null
			old_head.set_item(null);
			// decrement the size of the stack
			size--;
		
			// return the pop_item
			return pop_item;
		
		}
		else // ifthere is nothing in the stack return null
		{
			return null;
		}
		
	}
		
	public boolean is_empty()
	{
		return size < 1; // is there anything in the stack?
	}
	
}
