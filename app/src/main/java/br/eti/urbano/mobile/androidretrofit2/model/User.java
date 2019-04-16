package br.eti.urbano.mobile.androidretrofit2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 28/09/18.
 */

/**
 * @link https://stackoverflow.com/questions/44948858/lombok-builder-on-a-class-that-extends-another-class
 * @link https://blog.codecentric.de/en/2016/05/reducing-boilerplate-code-project-lombok/
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}