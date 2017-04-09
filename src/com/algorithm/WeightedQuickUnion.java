package com.algorithm;

public class WeightedQuickUnion extends QuickUnion 
{

	private int[] _size;
	
	public WeightedQuickUnion(int n) 
	{
		super(n);
		_size = new int[n];
		for(int j = 0; j < n; j++)
			_size[j]=1;
	}

	@Override
	public boolean union( int p , int q)
	{
		int i = find(p);
		int j = find(q);
		
		if(i == j) return false;
		else {
			// Make smaller root point to larger one.
			if (_size[i] < _size[j]) {
				_sites[i] = j;
				_size[j] += _sites[i];
			} else {
				_sites[j] = i;
				_size[i] += _size[j];
			}
			_count--;
			return true;
		}
	}
	
	
}
