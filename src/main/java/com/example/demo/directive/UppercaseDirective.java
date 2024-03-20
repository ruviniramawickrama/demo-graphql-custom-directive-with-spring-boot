/**
 * @author  Ruvini Ramawickrama
 */
package com.example.demo.directive;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

public class UppercaseDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(
            SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> env) {

        // Skip fields for which 'uppercase' directive is not applied
        if (env.getAppliedDirective("uppercase") == null) {
            return env.getElement();
        }

        GraphQLFieldDefinition field = env.getElement();
        GraphQLFieldsContainer parentType = env.getFieldsContainer();

        // Build a data fetcher that transforms the given value to uppercase
        DataFetcher originalFetcher = env.getCodeRegistry().getDataFetcher(parentType, field);
        DataFetcher dataFetcher =
                DataFetcherFactories.wrapDataFetcher(
                        originalFetcher,
                        ((dataFetchingEnvironment, value) -> {
                            if (value instanceof String) {
                                return ((String) value).toUpperCase();
                            }
                            return value;
                        }));

        // Change the field definition to use the new uppercase data fetcher
        env.getCodeRegistry().dataFetcher(parentType, field, dataFetcher);
        return field;
    }
}
