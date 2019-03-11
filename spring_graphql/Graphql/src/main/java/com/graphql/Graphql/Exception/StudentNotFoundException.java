package com.graphql.Graphql.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class StudentNotFoundException  extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 302431707560510800L;
	
	
	
	private Map<String, Object> extensions = new HashMap<>();

    public StudentNotFoundException(String message, Long invalidStudentId) {
        super(message);
        extensions.put(" In Valid Student ID ", invalidStudentId);
    }
    
    public StudentNotFoundException(String message, String name ) {
        super(message);
        extensions.put("Name  ", name);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
