/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geradorqrcode.controller;

import geradorqrcode.business.GeraQrCode;
import geradorqrcode.business.IStrategy;
import geradorqrcode.business.ImprimeCodigo;
import geradorqrcode.model.EntidadeNegocio;

/**
 *
 * @author noriaki.odan
 */
public class Fachada {

    private IStrategy rn;
    
    public Fachada() {
    }
    
    public String gerarCodigo(EntidadeNegocio entidade) {
        rn = new GeraQrCode();
        String msg = rn.execute(entidade);
        System.out.println(msg);
        return msg;
    }

    public String imprimir(EntidadeNegocio entidade) {
        rn = new ImprimeCodigo();
        String msg = rn.execute(entidade);
        System.out.println(msg);
        return msg;
    }
}
