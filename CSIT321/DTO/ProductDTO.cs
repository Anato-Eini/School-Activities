using System.ComponentModel.DataAnnotations;

namespace ANI.DTO;

/// <summary>
/// Represents the base data transfer object for a product.
/// </summary>
public abstract class ProductBaseDTO
{
    /// <summary>
    /// Gets or sets the name of the product.
    /// </summary>
    [Required]
    public string Name { get; set; } = null!;

    /// <summary>
    /// Gets or sets the description of the product.
    /// </summary>
    public string? Description { get; set; }

    /// <summary>
    /// Gets or sets the price of the product.
    /// </summary>
    [Required]
    public decimal Price { get; set; }

    /// <summary>
    /// Gets or sets the stock quantity of the product.
    /// </summary>
    [Required]
    [Range(1, int.MaxValue, ErrorMessage = "Stock must be more than 0")]
    public int Stock { get; set; }

    /// <summary>
    /// Gets or sets the user ID associated with the product.
    /// </summary>
    [Required]
    public Guid UserID { get; set; }
}

/// <summary>
/// Represents the data transfer object for a product response.
/// </summary>
public class ProductResponseDTO : ProductBaseDTO
{
    /// <summary>
    /// Gets or sets the unique identifier for the product.
    /// </summary>
    public Guid ProductID { get; set; }

    /// <summary>
    /// Gets or sets the URL of the product picture.
    /// </summary>
    public string ProductPictureUrl { get; set; } = null!;
}

/// <summary>
/// Represents the data transfer object for creating a product.
/// </summary>
public class ProductCreateDTO : ProductBaseDTO
{
    /// <summary>
    /// Gets or sets the picture file of the product.
    /// </summary>
    [Required]
    public IFormFile ProductPictureUrl { get; set; } = null!;
}

/// <summary>
/// Represents the data transfer object for updating a product.
/// </summary>
public class ProductUpdateDTO : ProductBaseDTO
{
    /// <summary>
    /// Gets or sets the unique identifier for the product.
    /// </summary>
    [Required]
    public Guid ProductID { get; set; }

    /// <summary>
    /// Gets or sets the picture file of the product.
    /// </summary>
    public IFormFile? ProductPictureUrl { get; set; }
}

/// <summary>
/// Represents the data transfer object for a secondary product.
/// </summary>
public class ProductSecDTO : ProductBaseDTO
{
    /// <summary>
    /// Gets or sets the URL of the product picture.
    /// </summary>
    public string ProductPictureUrl { get; set; } = null!;
}