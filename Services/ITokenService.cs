using ANI.DTO;

namespace ANI.Services;

public interface ITokenService{
    public string GenerateToken(UserLoginDTO userLoginDTO);
    public string ValidateToken(string token);
}