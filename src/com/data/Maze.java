/**
 * 
 */
package com.data;

import java.util.ArrayList;

/**
 * @author Dawson Valdes
 *
 */

public class Maze
{

	private class Cell
	{
		
		private int _north;
		private int _south;
		private int _east;
		private int _west;
		
		public Cell( )
		{
			_north = -1;
			_south = -1;
			_east = -1;
			_west = -1;
		}

		public int get_north() 
		{
			return _north;
		}

		public void set_north(int c) 
		{
			_north = c;
		}

		public int get_south() 
		{
			return _south;
		}

		public void set_south(int c) 
		{
			_south = c;
		}

		public int get_east() 
		{
			return _east;
		}

		public void set_east(int c) 
		{
			_east = c;
		}

		public int get_west() 
		{
			return _west;
		}

		public void set_west(int c) 
		{
			_west = c;
		}
				
	}
	
	private int maze_height;
	private int maze_width;
	private int maze_length;
	private int north_bound;
	private int south_bound;
	
	private Cell[] maze_cells;
	
	public Maze( int height , int width )
	{
		maze_height = height;
		maze_width = width;
		
		maze_length = height*width;
		
		north_bound = maze_width - 1;
		south_bound = maze_length - maze_width;
		
		maze_cells = new Cell[maze_length];
		
		init_maze();
	}
	
	private void init_maze()
	{
			
		int i = 0;
		
		while( i < maze_length )
		{
			
			boolean broken = false;
			
			ArrayList<Character> ordinals  = new ArrayList<Character>();
			
			if ( maze_cells[i].get_north() < 0) ordinals.add('n');
			if ( maze_cells[i].get_south() < 0) ordinals.add('s');
			if ( maze_cells[i].get_east() < 0 ) ordinals.add('e');
			if ( maze_cells[i].get_west() < 0 ) ordinals.add('w');
					
			while( !(broken) && ordinals.size() > 0 )
			{				
				int wall = (int) Math.floor( Math.random() * ordinals.size() );
				
				broken = break_wall( i , ordinals.get(wall) );
				ordinals.remove(wall);
			}	
			
			i++;
			
		}
		
	}
	
	public Cell[] get_maze()
	{
		return maze_cells;
	}
		
	public void print_maze()
	{
		
	}
	
	private boolean break_wall( int cell_num , char wall_ord )
	{
		switch ( wall_ord )
		{
		
			case 'n':
				if( cell_num > north_bound )
				{ 
					maze_cells[ cell_num ].set_north( cell_num - maze_width ); 
					maze_cells[ cell_num - maze_width ].set_south( cell_num );
					return true;
				}break;
		
		
			case 's':
				if( cell_num < south_bound )
				{
					maze_cells[ cell_num ].set_south( cell_num + maze_width );
					maze_cells[ cell_num + maze_width ].set_north( cell_num );
					return true;
				}break;
			
			case 'e':
				if( cell_num % maze_width > 0 )	
				{
					maze_cells[ cell_num ].set_east(cell_num+1);
					maze_cells[ cell_num - 1].set_west(cell_num);
					return true;
				}break;
				
			case 'w':
				if( cell_num % maze_width < maze_width ) //might need padding
				{
					maze_cells[ cell_num ].set_west( cell_num - 1 );
					maze_cells[ cell_num ].set_east(cell_num);
					return true;
				}break;
			
			default : break;
		}
		
		return false;		
				
	}
	
}
