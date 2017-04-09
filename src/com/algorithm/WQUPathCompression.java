package com.algorithm;

public class WQUPathCompression extends WeightedQuickUnion 
{

	public WQUPathCompression(int n) 
	{
		super(n);
	}

	@Override
	public int find(int f)
	{
		//path compression connects all nodes encountered along the way to the root 
		
		int rutt = f;
		
		while( rutt != _sites[rutt])
			rutt = _sites[rutt];
		
		while( f != rutt )
		{
			int new_f = _sites[f];
			_sites[f] = rutt;
			f = new_f;
		}
		
		return rutt;
	}
	
}
