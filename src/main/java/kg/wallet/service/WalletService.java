package kg.wallet.service;

import kg.wallet.dao.WalletDao;
import kg.wallet.model.User;
import kg.wallet.model.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/wallets")
public class WalletService {
    private WalletDao walletDao=new WalletDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Wallet> getAllWallets_Json(){return  walletDao.getWallets();}
    @GET
    @Path("/{wallet_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Wallet getWallet(@PathParam("walletId")Integer walletId){return walletDao.getWalletById(walletId);}

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Wallet updateWallet(Wallet wallet){
        return walletDao.updateWallet(wallet);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Wallet createWallet(Wallet wallet){
        return walletDao.createWallet(wallet);
    }

    @DELETE
    @Path("/{walletId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteWallet(@PathParam("walletId") int walletId) {
        return walletDao.deleteById(walletId) ? "deleted" : "not deleted";
    }
}
