package com.tandong.smarttv.view;

/**
 * Created by office on 2018/3/29.
 */

public interface FacetProvider {

    /**
     * Queries optional implemented facet.
     * @return Facet implementation for the facetClass or null if feature not implemented.
     */
    public Object getFacet(Class<?> facetClass);

}
