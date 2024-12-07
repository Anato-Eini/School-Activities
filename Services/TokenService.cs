/* using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.IdentityModel.Tokens;
using ANI.DTO;

namespace ANI.Services;

public class TokenService(IConfiguration configuration) : ITokenService
{
    private readonly IConfiguration _configuration = configuration;

    private readonly string SecretKey = "dfKei310dR3R+ad7xT3B87/4dEwk6a+NbeBAC4iZp8A=";

    public string GenerateToken(UserLoginDTO userLoginDTO)
    {
        var claims = new[]
               {
            new Claim(ClaimTypes.NameIdentifier, userLoginDTO.Id.ToString() ?? string.Empty),
            new Claim(ClaimTypes.Name, userLoginDTO.Username)
        };

        var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(secretKey));
        var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

        var token = new JwtSecurityToken(
            issuer: "your-issuer", // The issuer configured in your app
            audience: "your-audience", // The audience configured in your app
            claims: claims,
            expires: DateTime.Now.AddHours(1),
            signingCredentials: creds
        );

        return new JwtSecurityTokenHandler().WriteToken(token);
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

            JwtSecurityToken jwtToken = (JwtSecurityToken)validatedToken;
            string username = jwtToken.Claims.First(x => x.Type == ClaimTypes.Name).Value;

            return username;
        }
        catch
        {
            throw new SecurityTokenException("Invalid token " + token);
        }
    }
} */