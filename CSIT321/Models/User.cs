namespace ANI.Models;

/// <summary>
/// Represents a user in the ANI system.
/// </summary>
public class User
{
    /// <summary>
    /// Gets or sets the unique identifier for the user.
    /// </summary>
    [System.ComponentModel.DataAnnotations.Key]
    public Guid UserID { get; set; }

    /// <summary>
    /// Gets or sets the username of the user.
    /// </summary>
    public string Username { get; set; } = null!;

    /// <summary>
    /// Gets or sets the password of the user.
    /// </summary>
    public string Password { get; set; } = null!;

    /// <summary>
    /// Gets or sets the first name of the user.
    /// </summary>
    public string FirstName { get; set; } = null!;

    /// <summary>
    /// Gets or sets the last name of the user.
    /// </summary>
    public string LastName { get; set; } = null!;

    /// <summary>
    /// Gets or sets the phone number of the user.
    /// </summary>
    public string PhoneNumber { get; set; } = null!;

    /// <summary>
    /// Gets or sets the address of the user.
    /// </summary>
    public string Address { get; set; } = null!;

    /// <summary>
    /// Gets or sets a value indicating whether the user is a staff member.
    /// </summary>
    public bool IsStaff { get; set; } = false;

    /// <summary>
    /// Gets or sets a value indicating whether the user is a farmer.
    /// </summary>
    public bool IsFarmer { get; set; } = false;

    /// <summary>
    /// Gets or sets the URL of the user's profile picture.
    /// </summary>
    public string ProfilePictureUrl { get; set; } = null!;

    /// <summary>
    /// Gets or sets the collection of products associated with the user.
    /// </summary>
    public virtual ICollection<Product> Products { get; set; } = [];
}