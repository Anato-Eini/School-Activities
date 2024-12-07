using System.ComponentModel.DataAnnotations;

namespace ANI.DTO
{
    public class UserLoginDTO
    {
        [Required]
        public string Username { get; set; } = null!;
        [Required]
        public string Password { get; set; } = null!;
    }
}