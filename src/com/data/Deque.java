/**
 * 
 * Generic Double Ended Queue using encapsulation
 * with
 * Double Ended Nodes 
 * and
 * Recursive Finds 
 *  
 */

package com.data;


/**
 * @author Dawson Valdes
 *
 *	Berkeley City College
 *	Data Structure and Algorithms
 *	Spring 2017
 *
 */

public class Deque<ItemType>
{
	
	private class DoubleNode
	{
		private DoubleNode _next; // points to next node
		private DoubleNode _back; // points to previous node
		private ItemType _item; // points to item of data of ItemType
		
		public DoubleNode( ItemType item, DoubleNode next, DoubleNode back )
		{
			_next = next;  // assigns inital value to next
			_back = back;  // assigns inital value to back
			_item = item;  // assigns inital value to item
		}
		
		public ItemType item()
		{
			return _item;  // access private item
		}		
		
		public DoubleNode next()
		{
			return _next;  // accesses private next pointer
		}
		
		public DoubleNode back()
		{
			return _back;  // access private back pointer
		}
				
		public void set_next(DoubleNode next)
		{
			_next = next;  // sets private next pointer
		}
		
		public void set_back(DoubleNode back)
		{
			_back = back;  // sets private back pointer
		}
				
		public void set_item(ItemType item)
		{
			_item = item;  // sets private item
		}
		
	}
	
	private DoubleNode head; // points to first node of link list
	private DoubleNode tail; // points to last node of link list
	private int size; // tracks how many nodes are in link
		
	public Deque( )
	{
		head = tail = null; //point the head to the tail, point the tail to a null
		size = 0;		// set the size to 0
	}
		
	public boolean is_empty() // checks if the data structure is empty
	{
		return size > 0; // if the size is more than 0  it returns true, otherwise it returns false
	}
	
	public int size()
	{
		return size;  // returns the number of nodes in the data structure
	}
	
	public void add_at_front( ItemType item ) // adds a new node to the front of the data structure
	{	
		DoubleNode new_node = new DoubleNode( item , head , null ); //creates new node
		head = new_node; // points head of data structure to new node
		size++; // increments size of data structure
	}
		
	public void add_at_back( ItemType item) // adds a new node to the back of the data structure
	{
		DoubleNode new_node = new DoubleNode( item , null , tail); //creates new node
		tail = new_node; // points tail of data structure to new node
		size++; // increments size of data structure
	}
	
	public ItemType get_from_front( ) // removes node from front of data structure
	{
		if ( is_empty() ) // check if data structure is empty
		{
			return null; // if the structure is empty return a null
		}		
		else
		{	
			DoubleNode old_head = head; // make the current head the old head
			ItemType front_item = old_head.item(); // store the front item from the old head
		
			head = head.next(); // point the head to the next head
			
			old_head.set_next(null); // set the old head next to null to break references
			old_head.set_item(null); // set the old head item to null to break references
			
			size--; // decrement data structure size
			
			return front_item; // return the front item
		}					
	}
		
	public ItemType get_from_back( )
	{
		if ( is_empty() ) // check if data structure is empty
		{
			return null; // if the structure is empty return null
		}
		else
		{	
			DoubleNode old_tail = tail; // make the current tail the old tail
			ItemType back_item = old_tail.item(); // store the tail item from the old  tail
			
			tail = tail.back(); // point the tail to the previous item
			
			old_tail.set_back(null); // set the old tail to null to break reference
			old_tail.set_item(null); // set the old tail item to null to reference
			
			size--; // decrement the data structure size
			
			return back_item; // return the back item
		}
	}	
	
	private DoubleNode find_from_front( DoubleNode cursor , ItemType target )
	{
		// if the cursor is null return a null
		if( cursor == null )
		{
			return null;
		}
		// if the cursor item is the target return the cursor
		else if( cursor.item() == target ) 
		{
			return cursor;
		}
		// recurse the function moving to the next node in the list
		else 
		{
			return find_from_front( cursor.next(), target );  
		}
		
	}
		
	public boolean add_before_target( ItemType item , ItemType target )
	{		
		if ( is_empty() ) // check to is if the structure is empty
		{
			return false; // if it is return false, there is nothing to add before
		}
		else
		{
			// search the list from the front for the target starting from the head
			DoubleNode cursor = find_from_front( head , target );
			
			if (cursor == null)// if the cursor == null the target was not found
			{
				//return false , there is nothing to add before
				return false;
			}
			else // if a cursor is found
			{
				// create a pointer to the node before the cursor
				DoubleNode back_node = cursor.back();
				// create a new node containing item, pointing to cursor next and back node back
				DoubleNode new_node = new DoubleNode( item , cursor , back_node ); 
				// point the back nodes next to the new node
				back_node.set_next(new_node);
				// point the cursors back to the new node
				cursor.set_back(new_node);
				// increment the size of the data structure by one
				size++;
				// return true , a successful add
				return true;
			}						
		}		
	}
	
	private DoubleNode find_from_back( DoubleNode cursor , ItemType target )
	{
		// if the cursor is null return a null
		if( cursor == null )
		{
			return null;
		}
		// if the cursor item is the target return the cursor		
		else if (cursor.item() == target)
		{
			return cursor;
		}
		// recurse the function moving to the previous node in the list
		else 
		{
			return find_from_back( cursor.back(), target );
		}		
	}
	
	public boolean add_after_target( ItemType item , ItemType target )
	{
		if ( is_empty() ) // check to is if the structure is empty
		{
			return false; // if it is return false, there is nothing to add after
		}
		else
		{	
			// search the list from the back for the target starting from the tail
			DoubleNode cursor = find_from_back( tail , target);	
			
			// if cursor is == to null
			if (cursor == null)
			{
				return false; // return false no target was found
			}
			else
			{
				// make a pointer to the node after the cursor
				DoubleNode next_node = cursor.next();
				// create a new node containing an item pointing the next node next, and the cursor  for back
				DoubleNode new_node = new DoubleNode( item , next_node , cursor );
				// point the next_nodes back to the new node
				next_node.set_back(new_node);
				// point the cursor next to the new node
				cursor.set_next(new_node);
				// increment the size of the data structure
				size++;
				// return true , a succesful add
				return true;
			}
		}
	}
	
	public boolean remove_target( ItemType item )
	{
		if ( is_empty() ) // if the structure is empty
		{
			return false; // return false there is nothing to remove
		}
		else
		{
			// search the list from the front for the target starting from the head 
			DoubleNode cursor = find_from_front( head , item);	
			if (cursor == null) // if the cursor is null
			{
				return false; // return false, no target to remove
			}
			else
			{		
				DoubleNode next_node = cursor.next(); // create a pointer to the back node
				DoubleNode back_node = cursor.back(); // create a pointer to the next node
				
				back_node.set_next(next_node); // set the back nodes next to the next node
				next_node.set_back(back_node); // set the next nodes back to the back node
				
				cursor.set_next(null); // remove reference to next node
				cursor.set_back(null); // remove reference to back node
				cursor.set_item(null); // remove reference to item
				
				return true; // return true, successful removal
			}
		}
	}
	
	public boolean move_to_front( ItemType item )
	{
		if ( is_empty() ) // if the structure is empty
		{
			return false; // return false , there is nothing to move
		}
		else
		{	
			// find the item from the front, make a pointer to it
			DoubleNode cursor = find_from_front( head , item); 
			
			if (cursor == null) // if the cursor is null
			{
				return false; // return false , the item was not found
			}
			else
			{				
				DoubleNode next_node = cursor.next(); // create a pointer to the next node
				DoubleNode back_node = cursor.back(); // create a pointer to the back node
				
				next_node.set_back(back_node); // set the next nodes back to the back node
				back_node.set_next(next_node); // set the back nodes next to the next node
				
				cursor.set_back(null); // set the cursors back to null
				cursor.set_next(head); // set the cursors next to the head
				
				head.set_back(cursor); // set the heads back to the cursor
				head = cursor; // set the head to the cursor
				
				return true; // return true , a successful move to front
			}				
		}
	}
	
	public boolean move_to_back( ItemType item )
	{
		
		if ( is_empty() ) // if the structure is empty
		{
			return false; // return false, there is nothing to move
		}
		else
		{
			// find the item starting from the tail , make a pointer to it
			DoubleNode cursor = find_from_back( tail , item ); 
			
			if (cursor == null) // if the cursor == null
			{
				return false; // return false , the item was not found
			}
			else
			{
				DoubleNode next_node = cursor.next(); // create a pointer to the next node
				DoubleNode back_node = cursor.back(); // create a pointer to the back node
				
				next_node.set_back(back_node); // set the next node back to the back node
				back_node.set_next(next_node); // set the back node next to the back node
				
				cursor.set_back(tail); // set the cursor back to the tail
				cursor.set_next(null); // set the cursor next to null
				
				tail.set_next(cursor); // set the tail next to the cursor
				tail = cursor; // set the tail to the cursor
				
				return true; // return true, a successful move
			}				
		}
	}
	
}
