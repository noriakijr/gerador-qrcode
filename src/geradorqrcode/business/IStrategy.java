/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geradorqrcode.business;

import geradorqrcode.model.EntidadeNegocio;

/**
 *
 * @author noriaki.odan
 */
public interface IStrategy {
    
    public String execute(EntidadeNegocio entidade);
}
