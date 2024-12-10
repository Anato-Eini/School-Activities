using System.ComponentModel.DataAnnotations;

namespace ANI.DTO;

/// <summary>
/// Data Transfer Object for user security information.
/// </summary>
public class UserSecDTO
{
    /// <summary>
    /// Gets or sets the username.
    /// </summary>
    public string Username { get; set; } = null!;

    /// <summary>
    /// Gets or sets the first name.
    /// </summary>
    public string FirstName { get; set; } = null!;

    /// <summary>
    /// Gets or sets the last name.
    /// </summary>
    public string LastName { get; set; } = null!;

    /// <summary>
    /// Gets or sets the phone number.
    /// </summary>
    public string PhoneNumber { get; set; } = null!;

    /// <summary>
    /// Gets or sets the address.
    /// </summary>
    public string Address { get; set; } = null!;

    /// <summary>
    /// Gets or sets a value indicating whether the user is staff.
    /// </summary>
    public bool IsStaff { get; set; } = false;

    /// <summary>
    /// Gets or sets a value indicating whether the user is a farmer.
    /// </summary>
    public bool IsFarmer { get; set; } = false;

    /// <summary>
    /// Gets or sets the profile picture URL.
    /// </summary>
    public string ProfilePictureUrl { get; set; } = null!;
}

public class UserLoginDTO
{

    /// <summary>
    /// Gets or sets the username.
    /// </summary>
    [Required]
    [MaxLength(50)]
    public string Username { get; set; } = null!;

    /// <summary>
    /// Gets or sets the password.
    /// </summary>
    [Required]
    [MaxLength(50)]
    [MinLength(8)]
    [DataType(DataType.Password)]
    public string Password { get; set; } = null!;
}

public class UserCreateDTO : UserLoginDTO
{
    /// <summary>
    /// Gets or sets the first name.
    /// </summary>
    [Required]
    [MaxLength(50)]
    public string FirstName { get; set; } = null!;

    /// <summary>
    /// Gets or sets the last name.
    /// </summary>
    [Required]
    [MaxLength(50)]
    public string LastName { get; set; } = null!;

    /// <summary>
    /// Gets or sets the phone number.
    /// </summary>
    [Required]
    [MaxLength(11), MinLength(11)]
    public string PhoneNumber { get; set; } = null!;

    /// <summary>
    /// Gets or sets the address.
    /// </summary>
    [Required]
    public string Address { get; set; } = null!;

    /// <summary>
    /// Gets or sets the profile picture file.
    /// </summary>
    public IFormFile? ProfilePictureUrl { get; set; }
}

public class UserUpdateDTO : UserCreateDTO
{
    /// <summary>
    /// Gets or sets the new password.
    /// </summary>
    [MaxLength(50)]
    [MinLength(8)]
    [DataType(DataType.Password)]
    public string? NewPassword { get; set; } = null!;    

    /// <summary>
    /// Gets or sets a value indicating whether the user is a farmer.
    /// </summary>
    [Required]
    public bool IsFarmer { get; set; }
}

public class UserResponseDTO : UserSecDTO
{
    /// <summary>
    /// Gets or sets the user ID.
    /// </summary>
    public Guid UserID { get; set; }
}