package br.eti.urbano.mobile.androidretrofit2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by bruno on 28/09/18.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultModel {
    protected Integer id;
    protected String data;
}
