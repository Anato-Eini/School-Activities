using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ANI.Models;

/// <summary>
/// Represents a product in the system.
/// </summary>
public class Product
{
    /// <summary>
    /// Gets or sets the unique identifier for the product.
    /// </summary>
    [Key]
    public Guid ProductID { get; set; }

    /// <summary>
    /// Gets or sets the name of the product.
    /// </summary>
    public string Name { get; set; } = null!;

    /// <summary>
    /// Gets or sets the description of the product.
    /// </summary>
    public string Description { get; set; } = null!;

    /// <summary>
    /// Gets or sets the price of the product.
    /// </summary>
    public decimal Price { get; set; }

    /// <summary>
    /// Gets or sets the stock quantity of the product.
    /// </summary>
    public int Stock { get; set; }

    /// <summary>
    /// Gets or sets the URL of the product picture.
    /// </summary>
    public string ProductPictureUrl { get; set; } = null!;

    /// <summary>
    /// Gets or sets the unique identifier of the user who owns the product.
    /// </summary>
    public Guid UserID { get; set; }

    /// <summary>
    /// Gets or sets the user who owns the product.
    /// </summary>
    [ForeignKey("UserID")]
    public virtual User User { get; set; } = null!;
}