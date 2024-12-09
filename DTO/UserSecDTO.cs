namespace ANI.DTO;

public class UserSecDTO
{
    public string Username { get; set; } = null!;
    public string FirstName { get; set; } = null!;
    public string LastName { get; set; } = null!;
    public string PhoneNumber { get; set; } = null!;
    public string Address { get; set; } = null!;
    public bool IsStaff { get; set; } = false;
    public bool IsFarmer { get; set; } = false;
    public string ProfilePictureUrl { get; set; } = null!;
}