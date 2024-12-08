using System.ComponentModel.DataAnnotations;

namespace ANI.DTO;

public class UserResponseDTO
{
    [Required]
    [MaxLength(50)]
    public string Username { get; set; } = null!;

    [Required]
    [MaxLength(50)]
    public string FirstName { get; set; } = null!;

    [Required]
    [MaxLength(50)]
    public string LastName { get; set; } = null!;

    [Required]
    [MaxLength(11), MinLength(11)]
    public string PhoneNumber { get; set; } = null!;
    public string? Address { get; set; }
    public bool IsStaff { get; set; } = false;
    public bool IsFarmer { get; set; } = false;
    public string ProfilePictureUrl { get; set; } = "/Media/Images/Profiles/blank-profile-picture-973460_128012234212.png";
}
