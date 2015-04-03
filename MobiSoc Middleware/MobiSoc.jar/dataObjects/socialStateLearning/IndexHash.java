package dataObjects.socialStateLearning;
import java.util.*;


public class IndexHash<K,V> {
	// uses a Vector to index a HashTable
	
	private Vector<K> index;
	private Hashtable<K,V> hash;
	
	public IndexHash()
	{
		index = new Vector<K>();
		hash = new Hashtable<K, V>();
	}
	
	public void clear()
	{
		synchronized(index)
		{
			index.clear();
			hash.clear();
		}
	}

	public boolean add(K idx, V v)
	{
		synchronized(index)
		{
			// check if exists.
			if (!contains(idx))
			{
				index.add(idx);
				hash.put(idx, v);
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public V get(K idx)
	{
		synchronized(index)
		{
			if (contains(idx))
			{
				return hash.get(idx);
			}
			else
			{
				return null;
			}
		}
	}
	
	public V get(int idx)
	{
		K key = getKey(idx);
		if (key != null)
		{
			return get(key);
		}
		else
		{
			return null;
		}
	}
	
	public int getIndex(K idx)
	{
		synchronized(index)
		{
			if (contains(idx))
			{
				return index.indexOf(idx);
			}
			else
			{
				return -1;
			}
		}
	}
	
	public K getKey(int idx)
	{
		synchronized(index)
		{
			if ((idx < index.size()) && (idx >= 0))
			{
				return index.elementAt(idx);
			}
			else
			{
				return null;
			}
		}
	}
	
	public boolean contains(K idx)
	{
		synchronized(index)
		{
			return index.contains(idx);
		}
	}
	
	public int size()
	{
		synchronized(index)
		{
			return index.size();
		}
	}
}
