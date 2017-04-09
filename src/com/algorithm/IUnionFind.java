package com.algorithm;

public interface IUnionFind
{
	int count();
	
	boolean connected(int p, int q);
	
	int find(int f);
	
	boolean union(int p, int q);
	
}
