package com.algorithm;

abstract class UF implements IUnionFind 
{

	protected int[] _sites; // access to component site index
	protected int _count; // number of components
	
	public UF(int n)
	{ 
		// Initialize component id array
		
		_count = n;
		_sites = new int[n];
		
		for( int i = 0; i < n; i++ )
			_sites[i]=i;
		
	}
	
	private final boolean valid_index(int i)
	{
		return ( i >= 0 && i < _sites.length );
	}
	
	
	@Override
	public final int count()
	{
		return _count;
	}

	@Override
	public final boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}

	@Override
	public abstract int find(int f);

	@Override
	public abstract boolean union(int p, int q);

}
