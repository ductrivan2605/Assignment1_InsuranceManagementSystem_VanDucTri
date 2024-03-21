package ClaimProcessManager;

import java.util.ArrayList;
import java.util.List;

import entities.Claim;

public class ClaimProcessManagerImpl implements ClaimProcessManager {
    private List<Claim> claims;

    public ClaimProcessManagerImpl() {
        this.claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim updatedClaim) {
        for (int i = 0; i < claims.size(); i++) {
            Claim claim = claims.get(i);
            if (claim.getId().equals(updatedClaim.getId())) {
                claims.set(i, updatedClaim);
                break;
            }
        }
    }

    @Override
    public void delete(String claimId) {
        claims.removeIf(claim -> claim.getId().equals(claimId));
    }

    @Override
    public Claim getOne(String claimId) {
        for (Claim claim : claims) {
            if (claim.getId().equals(claimId)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }
}
