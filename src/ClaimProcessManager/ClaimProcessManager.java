/**
 * @author <Van Duc Tri - s3978223>
 */
package ClaimProcessManager;
import entities.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
}

