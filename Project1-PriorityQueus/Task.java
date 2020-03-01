

 public class Task implements Comparable<Task> {
	 public int id;
	 public int priority;
	
	 public Task(int id, int priority) {
		 this.id = id;
		 this.priority = priority;
	 }
	 
	 @Override
	 public int compareTo(Task other) {
		 return this.priority - other.priority;
	 }

	@Override
	public String toString() {
		return "Task [id:" + id + ", priority:" + priority + "]";
	}

	public int getId() {
		return id;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + priority;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id != other.id)
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}	
}
