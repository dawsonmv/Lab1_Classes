package com.algorithm;

public class QuickUnion extends UF {

	public QuickUnion(int n) 
	{
		super(n);
	}

	@Override
	public int find(int f) 
	{	
		// Find component name
		while( f != _sites[f] )
			f=_sites[f];
		return f;
	}

	@Override
	public boolean union(int p, int q)
	{
		//give p and q the same root.
		int i = find(p);
		int j = find(q);
		
		if(i==j)
			return false;
		else
		{
			_sites[i] = j;
			_count--;
			return true;
		}
	}

}
