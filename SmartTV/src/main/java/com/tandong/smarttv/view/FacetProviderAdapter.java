package com.tandong.smarttv.view;

/**
 * Created by office on 2018/3/29.
 */

public interface FacetProviderAdapter {

    /**
     * Queries {@link } for a given type within Adapter.
     * @param type        type of the item.
     * @return Facet provider for the type.
     */
    public FacetProvider getFacetProvider(int type);

}
