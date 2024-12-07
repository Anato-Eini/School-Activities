using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.IdentityModel.Tokens;
using ANI.DTO;

namespace ANI.Services;

public class TokenService(IConfiguration configuration) : ITokenService
{
    private readonly IConfiguration _configuration = configuration;

    public string GenerateToken(UserLoginDTO userLoginDTO)
    {
        JwtSecurityTokenHandler tokenHandler = new();
        string? jwtKey = _configuration["Jwt:Key"];

        if (string.IsNullOrEmpty(jwtKey))
            throw new ArgumentNullException(nameof(jwtKey), "JWT key is not configured.");

        byte[] key = Encoding.ASCII.GetBytes(jwtKey);

        SecurityTokenDescriptor tokenDescriptor = new SecurityTokenDescriptor
        {
            Subject = new ClaimsIdentity(
            [
                new Claim(ClaimTypes.Name, userLoginDTO.Username)
            ]),
            Expires = DateTime.UtcNow.AddDays(7),
            SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
        };

        var token = tokenHandler.CreateToken(tokenDescriptor);

        return tokenHandler.WriteToken(token);
    }

    public string ValidateToken(string token)
    {
        JwtSecurityTokenHandler tokenHandler = new();
        string? jwtKey = _configuration["Jwt:Key"];

        if (string.IsNullOrEmpty(jwtKey))
            throw new ArgumentNullException(nameof(token), "JWT key is not configured.");

        byte[] key = Encoding.ASCII.GetBytes(jwtKey);

        try
        {
            tokenHandler.ValidateToken(token, new TokenValidationParameters
            {
                ValidateIssuerSigningKey = true,
                IssuerSigningKey = new SymmetricSecurityKey(key),
                ValidateIssuer = false,
                ValidateAudience = false,
                ClockSkew = TimeSpan.Zero
            }, out SecurityToken validatedToken);

            JwtSecurityToken jwtToken = (JwtSecurityToken) validatedToken;
            string username = jwtToken.Claims.First(x => x.Type == ClaimTypes.Name).Value;

            return username;
        }
        catch
        {
            throw new SecurityTokenException("Invalid token");
        }
    }
}