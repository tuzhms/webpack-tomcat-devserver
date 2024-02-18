import * as React from 'react'

export interface ButtonProps {
    readonly href: string,
    readonly label: string
}

const Button: React.FC<ButtonProps> = ({href, label}) => <a href={href} style={{
    border: "2px solid darkolivegreen",
    color: "darkolivegreen",
    padding: "2px 6px"
}}>{label}</a>

export default Button