import grails.util.Environment
import java.io.InputStream

import org.apache.lucene.analysis.standard.StandardTokenizerFactory
import org.apache.lucene.analysis.core.LowerCaseFilterFactory
import org.apache.lucene.analysis.ngram.NGramFilterFactory

def trace(args) {
	println args
}

String serverName = InetAddress.getLocalHost().getHostName()
println '======================================================================================================='
println ' '
println 'RUNNING APPLICATION ON: ' + serverName + ' - ENVIRONMENT=' + Environment.getCurrent();
println ' '
println '======================================================================================================='



grails.plugins.hibernatesearch = {
	rebuildIndexOnStart false
	throwOnEmptyQuery false
//	fullTextFilter name: "reserveContext", impl: ReserveContextFullTextFilterFactory, cache: 'none'

	analyzer( name: 'ngram', tokenizer: StandardTokenizerFactory ) {
		filter LowerCaseFilterFactory
		filter(NGramFilterFactory) {
			 param 'minGramSize', 3
			 param 'maxGramSize', 3
		}
	}

}

grails.databinding.convertEmptyStringsToNull = false

grails.controllers.upload.maxFileSize = 1024 * 1024 * 512;
grails.controllers.upload.maxRequestSize = 1024 * 1024 * 512;

grails.resources.processing.enabled=false

grails {
	mail {
	  host = "localhost"
	  port = 25
	}
}

grails.mail.default.from='"Admin" <noreply@quickstart.fr>'


trace("app configured - synchronizing database with model");
