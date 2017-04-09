package com.algorithm;

public class QuickFind extends UF 
{

	public QuickFind(int n) 
	{
		super(n);
	}

	@Override
	public int find(int f) 
	{
		return _sites[f];
	}

	@Override
	public boolean union(int p, int q)
	{
		// put p and q into the same component
		int pID = find(p);
		int qID = find(q);
		
		// do nothing if p and q are already in the same component
		if(pID == qID)
			return false;
		else
		{
			//Change values from id[p] to id[q]
			for (int i = 0; i < _sites.length; i++)
				if (_sites[i] == pID) _sites[i] = qID;
			_count--;
			return true;
		}

	}

}
