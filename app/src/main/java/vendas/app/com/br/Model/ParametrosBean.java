package vendas.app.com.br.Model;

public class ParametrosBean {

    private Integer mParamsUsuCodigo;
    private String mParamsImportarCliente;
    private String mParamsEndIpLocal;
    private String mParamsEndIpServer;
    private String mParamsEstoqueNegativo;
    private Integer mParamsDescontoVendedor;
    private String mParamsLogin;
    private String mParamsSenha;

    public ParametrosBean() {

    }

    public ParametrosBean(Integer mParamsUsuCodigo, String mParamsImportarCliente, String mParamsEndIpLocal, String mParamsEndIpServer, String mParamsEstoqueNegativo, Integer mParamsDescontoVendedor, String mParamsLogin, String mParamsSenha) {
        this.mParamsUsuCodigo = mParamsUsuCodigo;
        this.mParamsImportarCliente = mParamsImportarCliente;
        this.mParamsEndIpLocal = mParamsEndIpLocal;
        this.mParamsEndIpServer = mParamsEndIpServer;
        this.mParamsEstoqueNegativo = mParamsEstoqueNegativo;
        this.mParamsDescontoVendedor = mParamsDescontoVendedor;
        this.mParamsLogin = mParamsLogin;
        this.mParamsSenha = mParamsSenha;
    }

    public Integer getmParamsUsuCodigo() {
        return mParamsUsuCodigo;
    }

    public void setmParamsUsuCodigo(Integer mParamsUsuCodigo) {
        this.mParamsUsuCodigo = mParamsUsuCodigo;
    }

    public String getmParamsImportarCliente() {
        return mParamsImportarCliente;
    }

    public void setmParamsImportarCliente(String mParamsImportarCliente) {
        this.mParamsImportarCliente = mParamsImportarCliente;
    }

    public String getmParamsEndIpLocal() {
        return mParamsEndIpLocal;
    }

    public void setmParamsEndIpLocal(String mParamsEndIpLocal) {
        this.mParamsEndIpLocal = mParamsEndIpLocal;
    }

    public String getmParamsEndIpServer() {
        return mParamsEndIpServer;
    }

    public void setmParamsEndIpServer(String mParamsEndIpServer) {
        this.mParamsEndIpServer = mParamsEndIpServer;
    }

    public String getmParamsEstoqueNegativo() {
        return mParamsEstoqueNegativo;
    }

    public void setmParamsEstoqueNegativo(String mParamsEstoqueNegativo) {
        this.mParamsEstoqueNegativo = mParamsEstoqueNegativo;
    }

    public Integer getmParamsDescontoVendedor() {
        return mParamsDescontoVendedor;
    }

    public void setmParamsDescontoVendedor(Integer mParamsDescontoVendedor) {
        this.mParamsDescontoVendedor = mParamsDescontoVendedor;
    }

    public String getmParamsLogin() {
        return mParamsLogin;
    }

    public void setmParamsLogin(String mParamsLogin) {
        this.mParamsLogin = mParamsLogin;
    }

    public String getmParamsSenha() {
        return mParamsSenha;
    }

    public void setmParamsSenha(String mParamsSenha) {
        this.mParamsSenha = mParamsSenha;
    }
}
