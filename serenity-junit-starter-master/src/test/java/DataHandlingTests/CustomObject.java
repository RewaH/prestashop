package DataHandlingTests;
// like Person as a class name
	public class CustomObject {
	    private String name;
	    private int age;
	    private String city;
	    private String gender;
	    
		public CustomObject() {
			
		}
	    
		
		public CustomObject(String name, int age, String city) {
			this.name=name;
			this.age=age;
			this.city=city;
			// TODO Auto-generated constructor stub
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
}
