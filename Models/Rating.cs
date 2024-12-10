using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ANI.Models;

/// <summary>
/// Represents a rating given by a user to a product.
/// </summary>
public class Rating
{
    /// <summary>
    /// Gets or sets the unique identifier for the rating.
    /// </summary>
    [Key]
    public Guid RatingID { get; set; }

    /// <summary>
    /// Gets or sets the name of the rating.
    /// </summary>
    public string Name { get; set; } = null!;

    /// <summary>
    /// Gets or sets the content of the rating.
    /// </summary>
    public string Content { get; set; } = null!;

    /// <summary>
    /// Gets or sets the number of stars given in the rating.
    /// </summary>
    public int Stars { get; set; }

    /// <summary>
    /// Gets or sets the unique identifier of the product being rated.
    /// </summary>
    public Guid ProductID { get; set; }

    /// <summary>
    /// Gets or sets the unique identifier of the user who gave the rating.
    /// </summary>
    public Guid UserID { get; set; }

    /// <summary>
    /// Gets or sets the user who gave the rating.
    /// </summary>
    [ForeignKey("UserID")]
    public virtual User User { get; set; } = null!;

    /// <summary>
    /// Gets or sets the product being rated.
    /// </summary>
    [ForeignKey("ProductID")]
    public virtual Product Product { get; set; } = null!;
}