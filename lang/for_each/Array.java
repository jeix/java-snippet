package lang.for_each;

import java.util.ArrayList;
import java.util.List;

class Array<T> {
	
	List<T> items;
	
	public Array(List<T> items) {
		this.items = items;
	}
	
	public void for_each(Array.ForEach<T> func) {
		int ix = 0;
		for (T item : this.items) {
			func.apply(item, ix++);
		}
	}
	
	public boolean every(Array.Filter<T> filt) {
		for (T item : this.items) {
			if (! filt.apply(item)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean some(Array.Filter<T> filt) {
		for (T item : this.items) {
			if (filt.apply(item)) {
				return true;
			}
		}
		return false;
	}
	
	public List<T> filter(Array.Filter<T> filt, Object... vals) {
		List<T> results = new ArrayList<T>();
		for (T item : this.items) {
			if (filt.apply(item, vals)) {
				results.add(item);
			}
		}
		return results;
	}
	
	static class Converter<S,D> {
		
		List<S> srcs;
		List<D> dsts;
		
		public Converter(List<S> srcs, List<D> dsts) {
			this.srcs = srcs;
			this.dsts = dsts;
		}
		
		public List<D> map(Array.Map<S,D> map) {
			for (S src : this.srcs) {
				this.dsts.add(map.apply(src));
			}
			return this.dsts;
		}
	}
	
	static class Reducer<S,T> {
		
		List<S> srcs;
		
		public Reducer(List<S> srcs) {
			this.srcs = srcs;
		}
		
		public T reduce(Array.Reduce<S,T> reducer) {
			return reduce(reducer, null);
		}
		
		public T reduce(Array.Reduce<S,T> reducer, T dst) {
			for (S src : this.srcs) {
				dst = reducer.apply(dst, src);
			}
			return dst;
		}
		
		public T reduce_right(Array.Reduce<S,T> reducer) {
			return reduce_right(reducer, null);
		}
		
		public T reduce_right(Array.Reduce<S,T> reducer, T dst) {
			int size = srcs.size();
			for (int i = size - 1; i >= 0; i--) {
				S src = this.srcs.get(i);
				dst = reducer.apply(dst, src);
			}
			return dst;
		}
	}
	
	interface ForEach<T> {
		void apply(T param, int index);
	}
	
	interface Filter<T> {
		boolean apply(T param, Object... vals);
	}
	
	interface Map<S,D> {
		D apply(S param, Object... vals);
	}
	
	interface Reduce<S,T> {
		T apply(T accumulator, S param);
	}
}