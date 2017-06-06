package grails3.quick.start

import java.util.Date;
import java.util.List;

class BootStrap {

    def init = { servletContext ->
		
		println "BOOTSTRAP START"
		
		ExampleAggregateRoot.withTransaction {
		
			def category = new ExampleCategory();
			category.name = 'test';
			category.save(flush : true);
			
			def model = new ExampleAggregateRoot();
			model.categories.add(category);
			model.author = 'test';
			model.body = "test body"
			model.publishedDate = new Date();
			model.summary = 'summary'
			model.title = 'test title' 
			model.status = ExampleAggregateRoot.Status.ENABLED;
			model.price = 2.1;
			model.someInteger = 1;
			model.save(flush: true);
			
			println 'model created'
			
			ExampleAggregateRoot.search().createIndexAndWait()
	
		}
				
		println "BOOTSTRAP ENDED"
    }
	
	
    def destroy = {
    }
}
