using System.ComponentModel.DataAnnotations;

namespace ANI.DTO;

public class UserResponseDTO
{
    
    public string Username { get; set; } = null!;
    public string FirstName { get; set; } = null!;
    public string LastName { get; set; } = null!;
    public string PhoneNumber { get; set; } = null!;
    public string Address { get; set; } = null!;
    public bool IsStaff { get; set; } = false;
    public bool IsFarmer { get; set; } = false;
    public string ProfilePictureUrl { get; set; } = "/Media/Images/Profiles/blank-profile-picture-973460_128012234212.png";
}
